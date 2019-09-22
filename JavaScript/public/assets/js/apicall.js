console.log("fun");
window.onload = function(){
    getmethod();
};
const getmethod = async() =>{
    console.log("funcall");
    const response = await fetch('https://uselessfacts.jsph.pl/random.json?language=en');
    const uselessFact = await response.json();
    var  factsp = document.getElementById('factspace');
    factsp.innerHTML = uselessFact.text;
    console.log(uselessFact);
}