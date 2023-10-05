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
function loadingAlert()
{
  Swal.fire({
    title: 'Só um momento',
    html: 'estamos finalizando a sua inscrição',
    timer: 25000,    
    didOpen: () => {
        //exibir loadin
        Swal.showLoading();
        //desabilita o botao de submit
        document.querySelector("#submitButton").disabled = true;
    },
  })
}
