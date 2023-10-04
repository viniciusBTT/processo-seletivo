if(sucesso){            
    let timerInterval
        Swal.fire({
        icon: 'success',
          position: 'bottom-end',   
          html: sucesso,
          timer: 1700,
          timerProgressBar: true,     
          background: '#fff ',                  
          backdrop: "rgba(0, 0, 0, 0)" ,
          showConfirmButton: false,
        })
}        
else if(error)
{
    let timerInterval
        Swal.fire({
        icon: 'error',
          position: 'bottom-end',   
          html: error,
          timerProgressBar: true,     
          background: '#fff ',                  
          backdrop: "rgba(0, 0, 0, 0)" ,
        })
}