// import Dropdown from '/assets/vendors/govBR/dist/partial/js/behavior/dropdown.js'
// /**
//  * Classe para o exemplo do comportamento dropdown
//  */
// class BRAvatar {
//   /**
//    * Instancia um exemplo de comportamento dropdown
//    * @param {string} name - Nome do componente
//    * @param {object} component - Referência ao objeto do DOM
//    */
//   constructor(name, component) {
//     this.name = name
//     this.component = component
//     this._setBehavior()
//   }

//   /**
//    * Define os comportamentos do componente
//    * @private
//    */
//   _setBehavior() {
//     this._setDropdownBehavior()
//   }

//   /**
//    * Define os comportamentos do dropdown
//    * @private
//    */
//   _setDropdownBehavior() {
//     if (this.component.parentElement.dataset.toggle === 'dropdown') {
//       const config = {
//         iconToHide: 'fa-caret-up',
//         iconToShow: 'fa-caret-down',
//         trigger: this.component.parentElement,
//         useIcons: true,
//       }
//       const dropdown = new Dropdown(config)
//       dropdown.setBehavior()
//     }
//   }
// }

// export default BRAvatar;





//instacia do Step da pagina process/add
const stepList = [];
for (const brStep of window.document.querySelectorAll('.br-step')) {
  stepList.push(new core.BRStep('br-step', brStep));
}


//table List         
const tableList = []
for (const [index, brTable] of window.document
  .querySelectorAll('.br-table')
  .entries()) {
  tableList.push(new core.BRTable('br-table', brTable, index))
}


//check box
const checkboxList = []
for (const brCheckbox of window.document.querySelectorAll('.br-checkbox')) {
  checkboxList.push(new core.BRCheckbox('br-checkbox', brCheckbox))
}

//item
const itemList = []
for (const brItem of window.document.querySelectorAll('.br-item')) {
  itemList.push(new core.BRItem('br-item', brItem))
}


//lista
const listList = []
for (const brList of window.document.querySelectorAll('.br-list')) {
  listList.push(new core.BRList('br-list', brList))
}

//pagination
const paginationList = []
for (const brPagination of window.document.querySelectorAll('.br-pagination')) {
  paginationList.push(new core.BRPagination('br-pagination', brPagination))
}


//dateTimePicker
const datetimepickerList = []
for (const brDateTimePicker of window.document.querySelectorAll('.br-datetimepicker')) {
  datetimepickerList.push(
    new core.BRDateTimePicker('br-datetimepicker', brDateTimePicker))
}


//tags 
const tagList = []
for (const brTag of window.document.querySelectorAll('.br-tag')) {
  tagList.push(new core.BRTag('br-tag', brTag))
}

//carrosel
const carouselList = []
for (const brCarousel of window.document.querySelectorAll('.br-carousel')) {
  carouselList.push(new core.BRCarousel('br-carousel', brCarousel))
}

//inputs
const inputList = []
for (const brInput of window.document.querySelectorAll('.br-input')) {
  inputList.push(new core.BRInput('br-input', brInput))
}



//selects
const selectList = []
const notFoundElement = `
 <div class="br-item not-found">
  <div class="container">
   <div class="row">
    <div class="col">
     <p><strong>Ops!</strong> Não encontramos o que você está procurando!</p>
    </div>
   </div>
  </div>
 </div>
`
for (const brSelect of window.document.querySelectorAll('.br-select')) {
  const brselect = new core.BRSelect('br-select', brSelect, notFoundElement)
  //Exemplo de uso de listener do select
  brSelect.addEventListener('onChange', function (e) { })
  selectList.push(brselect)
}

//upload
const uploadList = []

function uploadTimeout() {
  return new Promise((resolve) => {
    // Colocar aqui um upload para o servidor e retirar o timeout
    return setTimeout(resolve, 3000)
  })
}

for (const brUpload of window.document.querySelectorAll('.br-upload')) {
  uploadList.push(new core.BRUpload('br-upload', brUpload, uploadTimeout))
}




//toast 
const alertList = []
for (const brAlert of window.document.querySelectorAll('.br-message')) {
  alertList.push(new core.BRAlert('br-message', brAlert))
}
