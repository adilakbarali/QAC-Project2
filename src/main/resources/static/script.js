"use strict";

// const getData = async () => {
//   let response = await fetch("/getAll");
//   if (response.status !== 200) {
//     throw new Error("Request failed!");
//   }
//   console.log("Request Successful!");
//   let jsonData = await response.json();
//   console.log(jsonData);
//   return jsonData.data;
// };

// let showData = async () => {
//   let returnedData = await getData();
//   paragraphToSelect.innerHTML = "";
//   for (let d=0;d<returnedData.length;d++) {
//     let div = document.createElement("div");
//     div.style = "margin:10px;";
//     div.innerHTML = "Brand:" + returnedData[d].brand + "Name:" + returnedData[d].name + "Description:" + returnedData[d].description;
//     paragraphToSelect.append(div);
//   }
// };

const paragraphToSelect = document.querySelector("#dataPara");
let refTable;
let tableExists = false;

function clearData(){
    if(tableExists == true){
       document.body.removeChild(refTable);  
    }
    tableExists=false;
}

let showData = async () => {
  let response = await fetch("/getAll");
  if (response.status !== 200) {
    throw new Error("Request failed!");
  }
  console.log("Request Successful!");
  let returnedData = await response.json();
  console.log(returnedData);
  if(tableExists == true){
      clearData();
  }
  refTable = document.createElement("table")
  refTable.border=1
  let refRowHeader= document.createElement("tr");
  let refDataHeader1=document.createElement("td");
  let refDataHeader2=document.createElement("td");
  let refDataHeader3=document.createElement("td");
  refDataHeader1.innerHTML="Brand";
  refDataHeader2.innerHTML="Name";
  refDataHeader3.innerHTML="Description";
  refRowHeader.appendChild(refDataHeader1);
  refRowHeader.appendChild(refDataHeader2);
  refRowHeader.appendChild(refDataHeader3);
  refTable.appendChild(refRowHeader)
  for(let i=0;i<returnedData.length;i++){
    showRecord(returnedData[i].brand, returnedData[i].name, returnedData[i].description);
  }
  tableExists = true;
};

function setupDrink() {
  const data = {
    name: document.getElementById("brand").value,
    description: document.getElementById("name").value,
    price: document.getElementById("description").value,
  };

  fetch("/create", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Success:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

let deleteData = async (i) => {
  let response = await fetch("/delete/" + i);
  if (response.status !== 204) {
    throw new Error("Request Failed!");
  }
  console.log("Request Successful!");
};

function showRecord(brand, name, description) {
  let refRow1 = document.createElement("tr");
  let refTd1 = document.createElement("td");
  let refTd2 = document.createElement("td");
  let refTd3 = document.createElement("td");
  let refDelBtn = document.createElement("input");
  let refUpdateBtn = document.createElement("input");
  refTd1.innerHTML = brand;
  refTd2.innerHTML = name;
  refTd3.innerHTML = description;
  refRow1.appendChild(refTd1);
  refRow1.appendChild(refTd2);
  refRow1.appendChild(refTd3);
  refTable.appendChild(refRow1);
  document.body.append(refTable);
}
