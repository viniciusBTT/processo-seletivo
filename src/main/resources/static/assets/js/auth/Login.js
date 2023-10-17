let username = document.getElementById("username")
let lblLogin = document.getElementById('lblUsername');
username.addEventListener('input', e => {

    if (e.target.value.length === 0) {
        VMasker(username).unMask();
        lblLogin.innerText = '';
        lblLogin.innerText = 'CPF ou Nome de Usuário';
        return;
    }

    if (!isNaN(e.target.value)) {
        VMasker(username).maskPattern("999.999.999-99");
        lblLogin.innerText = 'CPF';
    }
    else {
        lblLogin.innerText = 'Nome de Usuário';
    }
})
document.getElementById('formLogin').addEventListener('submit', e => {
    e.preventDefault();
    console.log(document.getElementById("username").value)
    if (!isNaN(username.value[0]))
        document.getElementById("username").value = document.getElementById("username").value.replaceAll(".", "").replaceAll("-", "");
    console.log(document.getElementById("username").value)
    e.currentTarget.submit();
})

function forgetPassword() {
    Swal.fire({
        title: 'Informe seu CPF para prosseguir com a troca de senha:',
        input: 'text',
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
            Swal.fire('CPF invalido', '', 'info')
    })
}


function confirmEmail(candidate) {
    Swal.fire({
        title: `Candidato ${candidate.candidate}`,
        text: `Email : ${candidate.email}`,
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sim',
        denyButtonText: `Não`,
    }).then((result) => {
        /* verificando a responsa da modal*/
        if (result.isConfirmed) {
            Swal.fire('Encaminhamos ao seu e-mail o link para realizar a alteração da senha', '', 'success')
            axios.post('/forgot/reset', {
                ForgotDTO: {
                    candidate: candidate.candidate,
                    email: candidate.email
                }
              })
              .then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.error(error);
              });
        }
        else if (result.isDenied)
            Swal.fire('Tente alterar a senha novamente', '', 'info')

    })
}
