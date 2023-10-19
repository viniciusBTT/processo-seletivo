// Mensagem padrão de sucesso
if(success){         
        Swal.fire({
          icon: 'success',
          html: success,
          timer: 1700,
          timerProgressBar: true,     
          background: '#f1f1f1',        
          showConfirmButton: false,        
        })
}        
// Mensagem padrão de erro
else if(error)
{
        Swal.fire({
        icon: 'error',
          html: error,
          timerProgressBar: true,     
          background: '#f1f1f1 ',                  
          backdrop: "rgba(0, 0, 0, 0)" ,
        })
}


//gatilho para tela de loading
function loadingAlert(msg)
{
  Swal.fire({
    title: 'Só um momento',
    html: msg,
    timer: 50000,    
    didOpen: () => {
        //exibir loadin
        Swal.showLoading();
        //desabilita o botao de submit
        document.querySelector("#submitButton").disabled = true;
    },
  })
}

//Confirmação de logout
function confirmLogout()
{
  Swal.fire({
    title: 'Tem certeza que deseja sair?',
    showCancelButton: true,
    cancelButtonText: 'Cancelar',
    confirmButtonText: 'Sim',
  }).then((result) => {    
    if (result.isConfirmed) {
      window.location.href = "/logout";
    } 
  })
}
