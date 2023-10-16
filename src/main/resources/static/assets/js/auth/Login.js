let username = document.getElementById("username")  
            let lblLogin = document.getElementById('lblUsername');
            username.addEventListener('input', e =>{

                if(e.target.value.length === 0)
                {
                    VMasker(username).unMask();
                    lblLogin.innerText = '';
                    lblLogin.innerText = 'CPF ou Nome de Usuário';
                    return;
                }

                if (!isNaN(e.target.value)) {
                    //alert('é char');
                    VMasker(username).maskPattern("999.999.999-99"); 
                    lblLogin.innerText = 'CPF';
                }
                else
                {
                    lblLogin.innerText = 'Nome de Usuário';                   
                }
            })
            document.getElementById('formLogin').addEventListener('submit', e => {
                e.preventDefault(); 
                console.log(document.getElementById("username").value)                
                if(!isNaN(username.value[0]))
                    document.getElementById("username").value = document.getElementById("username").value.replaceAll(".", "").replaceAll("-", "");
                    console.log(document.getElementById("username").value)                
                    e.currentTarget.submit();
            })

function forgetPassword()
{
    Swal.fire({
        title: 'Informe seu CPF para prosseguir com a troca de senha:',
        input: 'text',
        inputAttributes: {
          autocapitalize: 'off'
        },
        showCancelButton: true,
        cancelButtonText: 'calcelar',
        confirmButtonText: 'Prosseguir',
        showLoaderOnConfirm: true,
        preConfirm: (cpf) => {
            console.log(cpf)
          return fetch(`//api.github.com/users/${cpf}`)
            .then(response => {
              if (!response.ok) {
                throw new Error(response.statusText)
              }
              return response.json()
            })
            .catch(error => {
              Swal.showValidationMessage(
                `Por favor, verifique o seu CPF`
              )
            })
        },
        allowOutsideClick: () => !Swal.isLoading()
      }).then((result) => {
        console.log(result)
        if (result.isConfirmed) 
            confirmEmail()        
      })
}

function confirmEmail(username,email)
{
    Swal.fire({
        title: `Candidato "46069293835"`,
        text: 'Èste é o seu email: ',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sim',
        denyButtonText: `Não`,
      }).then((result) => {
        /* verificando a responsa da modal*/
        if (result.isConfirmed) 
        {
            Swal.fire('Encaminhamos ao seu e-mail o link para realizar a alteração da senha', '', 'success')
        }
        else if (result.isDenied) 
          Swal.fire('Tente alterar a senha novamente', '', 'info')
        
      })
}
