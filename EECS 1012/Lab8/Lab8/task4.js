function rollDice() {
  var num;
  total = 0;
  var output = "";

  for (i = 0; i < 3; i++) {
    for (j = 0; j < 2; j++) {
      num = Math.floor(Math.random() * 6);
      output += num;
      total += num;
      if (j != 1) {
        output += " ";
      }
    }
    if (i != 2) {
      output += ", ";
    }
  }
  output += "<br />Average = " + (total / 3).toFixed(2);
  document.getElementById("mydata").innerHTML = output;

  //   console.log(Math.floor(Math.random() * 6));
  //   console.log(num);
}
