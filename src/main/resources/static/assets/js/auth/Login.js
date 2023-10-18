//caturando o input de usarname/cpf e a sua label
let username = document.getElementById("username")
let lblLogin = document.getElementById('lblUsername');

//verificando o 1 caracter do input para aplicar 
username.addEventListener('input', e => {

    if (e.target.value.length === 0) {
        VMasker(username).unMask();
        lblLogin.innerText = '';
        lblLogin.innerText = 'CPF ou Nome de Usuário';
        return;
    }
    else if(e.target.value.length === 1)
    {    
        if (!isNaN(e.target.value)) {
            VMasker(username).maskPattern("999.999.999-99");
            lblLogin.innerText = 'CPF';
        }
        else {
            lblLogin.innerText = 'Nome de Usuário';
        }
    }
})
document.getElementById('formLogin').addEventListener('submit', e => {
    e.preventDefault();
    if (!isNaN(username.value[0]))
        document.getElementById("username").value = document.getElementById("username").value.replaceAll(".", "").replaceAll("-", "");
    e.currentTarget.submit();
})

function forgetPassword() {
    Swal.fire({
        title: 'Informe seu CPF para prosseguir com a troca de senha:',
        input: 'text',
        inputPlaceholder: 'Digite o seu CPF aqui',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        cancelButtonText: 'Calcelar',
        confirmButtonText: 'Prosseguir',
        showLoaderOnConfirm: true,
        preConfirm: (cpf) => {
            console.log(cpf)
            return axios.get('/forgot/find', {
                params: {
                    cpf: cpf.replace(/[^a-zA-Z0-9 ]/g,''),
                }
            })
            .then((response)=> {
                return response.data;
                
            }) .catch(function (error) {
                return null
              })
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.value)
            confirmEmail(result.value)
        else
            Swal.fire('CPF inválido.', '', 'info')
    })
}


function confirmEmail(candidate) {
    Swal.fire({
        title: `Candidato ${candidate.candidate}.`,
        text: `Este é o seu e-mail: ${candidate.email}?`,
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sim',
        denyButtonText: `Não`,
    }).then((result) => {
        /* verificando a responsa da modal*/
        Swal.fire({
            title: 'Só um momento',
            timer: 50000,    
            didOpen: () => {
                //exibir loadin
                Swal.showLoading();
                //desabilita o botao de submit
                document.querySelector("#submitButton").disabled = true;
            },
          })
        if (result.isConfirmed) {            
            axios.post('/forgot/reset', {     
                candidate: candidate.candidate,
                email: candidate.email            
              })
              .then(function (response) {
                Swal.fire('Um link de redefinição foi enviado ao seu e-mail.', '', 'success')
                console.log(response);
              })
              .catch(function (error) {
                Swal.fire('Falha ao enviar o seu e-mail, contate um administrador.', '', 'error')
                console.error(error);
              });
        }
        else if (result.isDenied)
        Swal.fire({
            title: `Tente novamente.`,
            didOpen: () => {
                Swal.hideLoading();
            },            
           
        })

    })
}
