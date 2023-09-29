let inputSpecialCondition = document.querySelector("#specialCondition");

function showSpecialCondition(){
    if(inputSpecialCondition.hasAttribute("disabled"))   
    {
        inputSpecialCondition.removeAttribute("disabled")
    }
    else
    {
        inputSpecialCondition.setAttribute("disabled","") 
        inputSpecialCondition.value = ""
    }
}




//Verificando se as senhas são iguais
let registerForm = document.querySelector("#formRegister")
registerForm.addEventListener("submit", e => {
    e.preventDefault();
    if(document.querySelector("#password").value === document.querySelector("#confirmPassword").value)
        e.currentTarget.submit();    
    else
        alert("À senhas não ")
})