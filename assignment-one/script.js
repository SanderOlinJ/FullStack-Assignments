let count = 0;
let content = document.getElementById("lorem-text");


function updateCounter(){
    let counter = document.getElementById("counter");
    counter.innerText = count;
}

function countUp(){
    count++;
    updateCounter();
}

function countDown(){
    count--;
    updateCounter();
}

function toggleContent(){
    if(content.style.opacity == '1'){
        content.style.opacity = '0';
    } else {
        content.style.opacity = '1';
    }
}
