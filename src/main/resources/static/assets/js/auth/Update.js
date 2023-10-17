let url = window.location.pathname
let token = url.split('/')[3]
document.querySelector('#token').value = token;

let formUpdate = document.querySelector('#formUpdate');
 
formUpdate.addEventListener('submit', e => {
    e.preventDefault();

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
        e.currentTarget.submit()
    }
})