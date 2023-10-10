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
function testaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
  if (strCPF == "00000000000") return false;

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

//cep
function verifyCEP(){
    //captura o valor do input do cep
    let cep = document.querySelector("#cep").value
    //verifica no end-point /adress?cep=*** que retorna os valores do enredeço
    axios.get(`/address?cep=${cep}`)
    .then(function (response)
    {
        let address = response.data
        //verificando se o cep informado é valido
        if(response.data.cep)
        {
            //colocando o valor retornado nos inputs
            document.querySelector("#logradouro").value = address.logradouro;
            document.querySelector("#districtName").value = address.district.name;
            document.querySelector("#city").value = address.district.city.name;
            document.querySelector("#cityID").value = address.district.city.id;
            document.querySelector("#districtId").value = address.district.id;
            document.querySelector("#stateID").value = address.district.city.state.uf;
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
            document.querySelector("#logradouro").value = ""
            document.querySelector("#districtName").value = ""
            document.querySelector("#city").value = ""
            document.querySelector("#cityID").value = ""
            document.querySelector("#districtId").value = ""
            document.querySelector("#stateID").value = ""
        }      
    })
    .catch(function (error) {
       console.log(error)
    })
}