
//instacia do Step da pagina process/add
const stepList = [] ;
for (const brStep of window.document.querySelectorAll('.br-step')) {
  stepList.push(new core.BRStep('br-step', brStep));
}