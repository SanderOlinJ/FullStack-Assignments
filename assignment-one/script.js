let count = 0;
let content = document.getElementById("lorem-text");
let array = [document.getElementById("li-1"),
document.getElementById("li-2"),
document.getElementById("li-3"),
document.getElementById("li-4"),
document.getElementById("li-5"),
document.getElementById("li-6")];
const words = ["lorem", "ipsum", "dolor", "sit", "amet",
"consectetur", "adipiscing", "elit", "sed", "do", "eiusmod",
"tempor", "incididunt", "ut", "labore", "et", "dolore",
"magna", "aliqua"];

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

function chooseFourRandomWordsFromArray(){
    let fourWord = [];
    for (let i = 0; i < 6; i++){
        let randomElement = words[Math.floor(Math.random() * array.length)];
        fourWord[i] = randomElement;
    }
    return fourWord;
}

function fillArrayWithWords(){
    const wordsGenerated = chooseFourRandomWordsFromArray();
    for (let i = 0; i < array.length; i++){
        let word = wordsGenerated[i];
        array[i].innerText = word;
    }
}