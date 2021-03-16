/* Task1.js - Add your Java Script Code Here */
function myFunction() {
  var p = document.getElementById("mydata");

  var x = Math.random();

  if (x >= 0.5) {
    p.innerHTML = "Tails";
  } else {
    p.innerHTML = "Heads";
  }
}
