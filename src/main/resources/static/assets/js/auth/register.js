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
    else if(!validCpf)
    {
        Swal.fire({
            icon: 'info',
            title: 'Este CPF já foi cadastrado',
            html:  "<a href='/acesso'>clique aqui para acessar o sistema</a> ",
            timerProgressBar: true,     
            background: '#f1f1f1 ',                  
            backdrop: "rgba(0, 0, 0, 0)" ,                     
            showConfirmButton: false,  
            })
    }
    else
    {
        e.currentTarget.submit();   
    }
})


function verifyCPF(){
    let cpfValide = document.querySelector('#cpf').value.replace(/[^a-zA-Z0-9 ]/g,'')
    
    if(testaCPF(cpfValid))
    {        //verificando se o cpf está valido e se já foi cadastrado
        axios.get(`/register/candidate?cpf=${cpfValid}`)
        .then(function (response) {
            console.log(response);
            cpfValid = response.data;

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
            console.error(`error: ${error}`);
        })
        return false
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