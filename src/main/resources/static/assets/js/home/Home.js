
message = JSON.parse(message.replaceAll('&quot;', '"'))
if (localStorage.getItem('readed') != message.id) 
{
    Swal.fire({
        icon: 'info',
        html: message.message,
        background: '#f1f1f1',
        showConfirmButton: true,
    }).then(result => {
        console.log(result.value)
        if (result.value) {
            localStorage.setItem('readed', message.id)
        }
    })
}
