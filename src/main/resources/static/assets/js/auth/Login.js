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