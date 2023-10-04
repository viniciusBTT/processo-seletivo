/**
 * Função usada para setar os valores dos input do tipo date pois como o padrao de governos instancia os 
 * inputs depois do carregamento da pagina sobrepondo os valores do thymeleaf.
 */
function setInputDate(){
    datetimepickerList.forEach(item => {
        item.calendar.setDate(new Date(item.component.getAttribute('data-input')));
    })
}


//Capturando o evento de submite do formulario, salvando a nova modalidade do processo seletivo
const modalityForm = document.querySelector("#modalityForm");
modalityForm.addEventListener("submit", (e) =>{
    e.preventDefault();
    let form = new FormData(modalityForm);
    let name = form.get("name");
    let processId = form.get("processId")

    axios.post('/process/modality', {
        name,processId       
      })
      .then(function (response) {
       
        document.querySelector("#modalitiesList").innerHTML += ` <span class="br-tag bg-green-cool-vivid-50 medium mb-2"
                                                                    <i class="fa-solid fa-file "></i>
                                                                    <span class="ml-2">${response.data.modalities[response.data.modalities.length - 1 ].name}</span>
                                                                </span>`
        document.querySelector("#modalitieName").value =  "";
      })
      .catch(function (error) {
        console.error(error);
      });
})


//alterando entre as telas
let cardProcess = document.querySelector(".process");
let cardModalities = document.querySelector(".modaliti");
let brStep = document.querySelector(".br-step");

function openDados()
{     
    if(cardProcess.classList.contains("d-none"))
    {
        cardProcess.classList.toggle("d-none")
        cardModalities.classList.toggle("d-none")
        
    }
}

function openModalities()
{
    if(cardModalities.classList.contains("d-none"))
    {
        cardProcess.classList.toggle("d-none")
        cardModalities.classList.toggle("d-none")
        brStep.attributes[1].value = 2
    }
}
if(success)
    openModalities()


