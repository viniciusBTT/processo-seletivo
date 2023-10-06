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

    //persistindo a nova modalidade no banco de dados
    axios.post('/process/modality', {
        name,processId       
      })
      .then(function (response) 
      {
        let modalitiesElement = "";
        response.data.modalities.forEach(item =>
        {
            modalitiesElement +=  ` <span class="br-tag interaction medium bg-green-cool-vivid-50" id="${item.id}"            ">
                                        <i class="fa-solid fa-file bg-green-cool-vivid-50"></i>
                                        <span class="ml-2">${item.name}</span>
                                        <button class="br-button bg-green-cool-vivid-50" type="button" aria-label="Fechar" 
                                        data-dismiss="interaction01" id="${item.id}" data-process="${response.data.id}"
                                            onclick="deleteModality(this.id, this.getAttribute('data-process'))">
                                            <i class="fas fa-times" aria-hidden="true"></i>
                                        </button>
                                    </span>`
        })
        //limpando o input de modalidades                                                        
        document.querySelector("#modalitieName").value =  "";
        //colocando a nova lista de itens no html
        document.getElementById("modalitiesList").innerHTML = modalitiesElement;
      })
      .catch(function (error) {
        console.error(error);
      });
})

//alterando entre as telas
let cardProcess = document.querySelector(".process");
let cardModalities = document.querySelector(".modaliti");
let brStep = document.querySelector(".br-step");
//função para abrir as informações de processo seltivo
function openDados()
{     
    if(cardProcess.classList.contains("d-none"))
    {
        cardProcess.classList.toggle("d-none");
        cardModalities.classList.toggle("d-none");  
    }
}

//função para abrir as modalidades do processo seltivo
function openModalities()
{
    if(cardModalities.classList.contains("d-none"))
    {
        cardProcess.classList.toggle("d-none");
        cardModalities.classList.toggle("d-none");
        brStep.attributes[1].value = 2;
    }
}


if(success)
    openModalities()


function deleteModality( idModality,processId){
    
    axios.delete(`/modality/${processId}/${idModality}`)
      .then(function (response) {
        let modalitiesList =  document.querySelectorAll(".interaction")
        modalitiesList.forEach(item =>{
            if(item.id === idModality)
                item.remove();       
        })
        console.log(response);
      })
      .catch(function (error) {
        console.error(error);
      });
    
   

}


