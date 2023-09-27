
//instacia do Step da pagina process/add
const stepList = [] ;
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
for (const brDateTimePicker of window.document.querySelectorAll( '.br-datetimepicker' )) {
  datetimepickerList.push(
    new core.BRDateTimePicker('br-datetimepicker', brDateTimePicker )) 
}

//tags 
const tagList = []
for (const brTag of window.document.querySelectorAll('.br-tag')) {
  tagList.push(new core.BRTag('br-tag', brTag))
}