let inputSpecialCondition = document.querySelector("#diseaseInducedDeficiency");

function showSpecialCondition(){
    if(inputSpecialCondition.hasAttribute("disabled"))   
    {
        inputSpecialCondition.removeAttribute("disabled")
    }
    else
    {
        inputSpecialCondition.setAttribute("disabled","") 
        inputSpecialCondition.value = " "
    }
}


let validCpf = null;

//Captura o envento de submit para fazer as devidas verificações
let registerForm = document.querySelector("#formRegister")
registerForm.addEventListener("submit", e => {
    e.preventDefault();

    document.querySelector('#cpf').value = document.querySelector('#cpf').value.replace(/[^a-zA-Z0-9 ]/g,'')   
    //se a senha não confere retorna msg de rro
    if(document.querySelector("#password").value !== document.querySelector("#confirmPassword").value)
    {
        Swal.fire({
            icon: 'error',
            html: 'As senhas digitadas nao conferem',
            timerProgressBar: true,     
            background: '#f1f1f1 ',                  
            backdrop: "rgba(0, 0, 0, 0)" ,
            })
    }       
    else
    {
        e.currentTarget.submit();   
    }
})

//verificar se o valor do cpf é valido e se já existe no banco de dados
function verifyCPF(){
    let cpfValide = document.querySelector('#cpf').value.replace(/[^a-zA-Z0-9 ]/g,'')
    
    if(testaCPF(cpfValide))
    {        //verificando se o cpf está valido e se já foi cadastrado
        axios.get(`/register/candidate?cpf=${cpfValide}`)
        .then(function (response) {
          console.log(response.data)
            Swal.fire({
                icon: 'info',
                title: 'Este CPF já foi cadastrado',
                html:  "<a href='/acesso'>clique aqui para acessar o sistema</a> ",
                timerProgressBar: true,     
                background: '#f1f1f1 ',                  
                backdrop: "rgba(0, 0, 0, 0)" ,                     
                showConfirmButton: false,  
                })

        })
        .catch(function (error) {
            console.log(`CPF valido e nunca salvo`);
        })
    }
    else
    {
        Swal.fire({
            icon: 'error',
            html: 'valor do cpf invalido',
            timerProgressBar: true,     
            background: '#f1f1f1 ',                  
            backdrop: "rgba(0, 0, 0, 0)" ,
            })
    
    }

    
}

//valida se o numero do cpf é valido ou nao
function testaCPF(strCPF)
{
    var Soma;
    var Resto;
    Soma = 0;
    //verifica se os caracteres do cpf são iguais    
    if (allCharecterEquals(strCPF) ) return false;

  for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
  Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;

  Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
    return true;
}



//verificando se todos os caracteres são iguais
function allCharecterEquals(str) 
{
    // Verifica se a string está vazia ou tem apenas um caractere
    if (str.length <= 1) {
        return true;
    }
    // Compara cada caractere com o primeiro
    const primeiroCaractere = str[0];
    for (let i = 1; i < str.length; i++) {
        if (str[i] !== primeiroCaractere) {
            return false;
        }
    }
    // Se nenhum caractere diferente for encontrado, todos são iguais
    return true;
}


//verificando o cep
function verifyCEP()
{
    //captura o valor do input do cep
    let cep = document.querySelector("#cep").value

    //verifica no end-point /adress?cep=*** o endereço correspondente ao cep;
    axios.get(`/address?cep=${cep}`)
    .then(function (response)
    {
        let address = response.data
        //verificando se o cep informado é valido
        if(response.data.cep)
        {
            setInputAdressValues(address.logradouro,address.district.name,
                            address.district.city.name,address.district.city.id,
                            address.district.id,address.district.city.state.uf);
        }    
        else
        {
            //mensagem de erro
            Swal.fire({
                icon: 'error',
                html: 'valor do CEP invalido',
                timerProgressBar: true,     
                background: '#f1f1f1 ',                  
                backdrop: "rgba(0, 0, 0, 0)" ,
            })
            //limpando os inputs 
            setInputAdressValues("","","","","","")
        }      
    })
    .catch(function (error) {
       console.log(error)
    })
}

//Setando os valoes nos inputs do endereços 
function setInputAdressValues(logradouro,districtName, cityName,cityId,districId,stateUF)
{
    document.querySelector("#logradouro").value = logradouro;
    document.querySelector("#districtName").value = districtName;
    document.querySelector("#city").value = cityName;
    document.querySelector("#cityID").value = cityId;
    document.querySelector("#districtId").value = districId;
    document.querySelector("#stateID").value = stateUF;
}