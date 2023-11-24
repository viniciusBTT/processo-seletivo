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
        
        //Tirando a mascara do cpf antes de enviar
        document.querySelector('#cpf').value = document.querySelector('#cpf').value.replace(/[^a-zA-Z0-9 ]/g,'')

        let passwordInput = document.querySelector('#password')
        //verificar se existe o campo senha, caso tem faz a verificação 
        if( passwordInput)
        {              
            //se a senha não confere retorna msg de erro
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
                e.currentTarget.submit();   
            }
        }
        else
        {
            e.currentTarget.submit();   
        }
    })


//verificar se o valor do cpf é valido e se já existe no banco de dados
function verifyCPF(){
    let cpfValide = document.querySelector('#cpf').value.replace(/[^a-zA-Z0-9 ]/g,'')
    
    if(testaCPF(cpfValide))
    {        //verificando se o cpf está valido e se já foi cadastrado
        axios.get(`/register/candidate?cpf=${cpfValide}`)
        .then(function (response) {
          console.log(response.data)
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
            console.log(`CPF valido e nunca salvo`);
        })
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

//valida se o numero do cpf é valido ou nao
function testaCPF(strCPF)
{
    var Soma;
    var Resto;
    Soma = 0;
    //verifica se os caracteres do cpf são iguais    
    if (allCharecterEquals(strCPF) ) return false;

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



//verificando se todos os caracteres são iguais
function allCharecterEquals(str) 
{
    // Verifica se a string está vazia ou tem apenas um caractere
    if (str.length <= 1) {
        return true;
    }
    // Compara cada caractere com o primeiro
    const primeiroCaractere = str[0];
    for (let i = 1; i < str.length; i++) {
        if (str[i] !== primeiroCaractere) {
            return false;
        }
    }
    // Se nenhum caractere diferente for encontrado, todos são iguais
    return true;
}


//verificando o cep
function verifyCEP()
{
    //captura o valor do input do cep
    let cep = document.querySelector("#cep").value

    //verifica no end-point /adress?cep=*** o endereço correspondente ao cep;
    axios.get(`/address?cep=${cep}`)
    .then(function (response)
    {
        let address = response.data
        //verificando se o cep informado é valido
        if(response.data.cep)
        {
            setInputAdressValues(address.logradouro,address.district.name,
                            address.district.city.name,address.district.city.id,
                            address.district.id,address.district.city.state.uf);
        }    
        else
        {
            //mensagem de erro
            Swal.fire({
                icon: 'error',
                html: 'valor do CEP invalido',
                timerProgressBar: true,     
                background: '#f1f1f1 ',                  
                backdrop: "rgba(0, 0, 0, 0)" ,
            })
            //limpando os inputs 
            setInputAdressValues("","","","","","")
        }      
    })
    .catch(function (error) {
       console.log(error)
    })
}

//Setando os valoes nos inputs do endereços 
function setInputAdressValues(logradouro,districtName, cityName,cityId,districId,stateUF)
{
    document.querySelector("#logradouro").value = logradouro;
    document.querySelector("#districtName").value = districtName;
    document.querySelector("#city").value = cityName;
    document.querySelector("#cityID").value = cityId;
    document.querySelector("#districtId").value = districId;
    document.querySelector("#stateID").value = stateUF;
}


//modal de termos de uso

function termsOfService(){
    Swal.fire({
      html: `
            <div class="text-justify">
                <h3 class="h3">Termos de Uso para o Sistema de Publicação de Processos Seletivos</h3>
                <ol>
                    <li class="text-weight-bold	mb-2">Aceitação dos Termos
                        <ol>
                            <li class="text-weight-regular	">1 acessar e usar o sistema de publicação de processos seletivos para a Prefeitura de Francisco Morato, você concorda e aceita integralmente estes Termos de Uso. Se não concordar com algum termo ou condição, por favor, não use o sistema.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold	mb-2">Uso Autorizado
                        <ol>
                            <li class="text-weight-regular">1 Este sistema destina-se exclusivamente à publicação de processos seletivos relacionados à Prefeitura de Francisco Morato.</li>
                            <li class="text-weight-regular">2 O usuário concorda em utilizar o sistema de maneira ética, responsável e em conformidade com todas as leis e regulamentações aplicáveis.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold	mb-2">Responsabilidades do Usuário
                        <ol>
                            <li class="text-weight-regular">1 O usuário é responsável por manter a confidencialidade de suas credenciais de acesso.</li>
                            <li class="text-weight-regular">2 Ao publicar informações no sistema, o usuário garante que todas as informações são precisas e atualizadas.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold mb-2"> Propriedade Intelectual
                        <ol>
                            <li class="text-weight-regular">1 Todo o conteúdo disponibilizado no sistema, incluindo textos, logotipos, imagens e outros materiais, é de propriedade da Prefeitura de Francisco Morato ou devidamente licenciado para uso.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold mb-2">Limitações de Responsabilidade
                        <ol>
                            <li class="text-weight-regular">1 O sistema é fornecido "como está", sem garantias de qualquer tipo, expressas ou implícitas.</li>
                            <li class="text-weight-regular">2 A Prefeitura de Francisco Morato não se responsabiliza por qualquer dano direto, indireto, incidental, consequencial ou especial decorrente do uso ou incapacidade de usar o sistema.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold mb-2">Alterações nos Termos
                        <ol>
                            <li class="text-weight-regular">1 Reservamo-nos o direito de modificar estes Termos de Uso a qualquer momento, mediante aviso prévio. O uso continuado do sistema após tais modificações constitui aceitação dos Termos atualizados.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold mb-2">Encerramento do Acesso
                        <ol>
                            <li class="text-weight-regular">1 A Prefeitura de Francisco Morato reserva-se o direito de encerrar o acesso de qualquer usuário ao sistema, a seu exclusivo critério, sem aviso prévio.</li>
                        </ol>
                    </li>
                    <li class="text-weight-bold mb-2">Disposições Gerais
                        <ol>
                            <li class="text-weight-regular">1 Estes Termos de Uso constituem o acordo completo entre o usuário e a Prefeitura de Francisco Morato em relação ao sistema de publicação de processos seletivos.</li>
                            <li class="text-weight-regular">2 Em caso de conflito entre estes Termos de Uso e qualquer legislação aplicável, a legislação prevalecerá na medida necessária.</li>
                        </ol>
                    </li>
                </ol>
            </div>
      `,
      width: "80%",
      padding: "1em",
      color: "#1351B4",
    });
}
