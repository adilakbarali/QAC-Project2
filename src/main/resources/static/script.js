"use strict";

let refTable;
let tableExists = false;
let mode = 0;

function clearData(){
    if(tableExists == true){
       document.body.removeChild(refTable);
    }
    tableExists=false;
}

function createTable(){
  refTable = document.createElement("table")
  refTable.style.margin="0 auto";
  refTable.border=1
  let refRowHeader= document.createElement("tr");
  let refDataHeader1=document.createElement("th");
  let refDataHeader2=document.createElement("th");
  let refDataHeader3=document.createElement("th");
  let refDataHeader4=document.createElement("th");
  refDataHeader1.innerHTML="ID";
  refDataHeader2.innerHTML="Brand";
  refDataHeader3.innerHTML="Name";
  refDataHeader4.innerHTML="Description";
  refRowHeader.appendChild(refDataHeader1);
  refRowHeader.appendChild(refDataHeader2);
  refRowHeader.appendChild(refDataHeader3);
  refRowHeader.appendChild(refDataHeader4);
  refTable.appendChild(refRowHeader);
  tableExists = true;
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
  createTable();
  for(let i=0;i<returnedData.length;i++){
    showRecord(returnedData[i].id, returnedData[i].brand, returnedData[i].name, returnedData[i].description, i+1);
  }
};

let showDataById = async () => {
  let i = document.getElementById("inputId").value;
  let response = await fetch("/get/" + i);
  if (response.status !== 200) {
    throw new Error("Request failed!");
  }
  console.log("Request Successful!");
  let returnedData = await response.json();
  console.log(returnedData);
  if(tableExists == true){
      clearData();
  }
  createTable();
  showRecord(returnedData.id, returnedData.brand, returnedData.name, returnedData.description, i);
}

function setupDrink() {
  let i;

  const data = {
    brand: document.getElementById("updateBrand").value,
    name: document.getElementById("updateName").value,
    description: document.getElementById("updateDescription").value,
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
      document.getElementById("inputId").value = data.id;
      showDataById();
    })
    .catch((error) => {
      console.error("Error:", error);
    });
    document.getElementById("inputId").value = "";
}

function updateDrink() {
  let i = document.getElementById("updateId").value;
  const data = {
    brand: document.getElementById("updateBrand").value,
    name: document.getElementById("updateName").value,
    description: document.getElementById("updateDescription").value,
  };

  fetch("/replace/" + i, {
    method: "PUT",
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
    document.getElementById("inputId").value = i;
    showDataById();
    document.getElementById("inputId").value = "";
}

let deleteDataBtn = async (i) => {
  let response = await fetch("/delete/" + i, {method: "DELETE"});
  if (response.status !== 204) {
    throw new Error("Request Failed!");
    return false;
  }
  console.log("Request Successful!");
  return true;
};

function deleteData(i){
  if (deleteDataBtn(i)){
    refTable.deleteRow(i);
  }
}

function showRecord(id, brand, name, description, index) {
  let refRow1 = document.createElement("tr");
  let refTd1 = document.createElement("td");
  let refTd2 = document.createElement("td");
  let refTd3 = document.createElement("td");
  let refTd4 = document.createElement("td");
  let refDelBtn = document.createElement("button");
  refDelBtn.className = "btn btn-danger";
  refDelBtn.innerHTML = "Delete";
  refDelBtn.onclick = function() { deleteData(index) };
  let refUpdateBtn = document.createElement("button");
  refUpdateBtn.className = "btn btn-warning";
  refUpdateBtn.innerHTML = "Update";
  refUpdateBtn.onclick = function() { showUpdate(index) };
  refTd1.innerHTML = id;
  refTd2.innerHTML = brand;
  refTd3.innerHTML = name;
  refTd4.innerHTML = description;
  refRow1.appendChild(refTd1);
  refRow1.appendChild(refTd2);
  refRow1.appendChild(refTd3);
  refRow1.appendChild(refTd4);
  refRow1.appendChild(refUpdateBtn);
  refRow1.appendChild(refDelBtn);
  refTable.appendChild(refRow1);
  document.body.append(refTable);
}

function toggleShowSearchId(){
  var element = document.getElementById("searchIdContainer");
  if(element.style.display === "none"){
    element.style.display = "block";
  } else {
    element.style.display = "none";
  }
}

function showUpdate(i){
  var element = document.getElementById("updateContainer");
  var uId = document.getElementById("updateId");
  var uIdLabel = document.getElementById("updateIdLabel");
  element.style.display = "block";
  uIdLabel.style.display = "block";
  uId.style.display = "block";
  uId.value = i;
  mode = 2;
  let updateInfo = refTable.rows[i].cells;
  document.getElementById("updateBrand").value = updateInfo[1].innerHTML;
  document.getElementById("updateName").value = updateInfo[2].innerHTML;
  document.getElementById("updateDescription").value = updateInfo[3].innerHTML;
}

function showCreate(){
  var element = document.getElementById("updateContainer");
  var uId = document.getElementById("updateId");
  var uIdLabel = document.getElementById("updateIdLabel");
  uId.style.display = "none";
  uIdLabel.style.display = "none";
  mode = 1;
  element.style.display = "block";
  document.getElementById("updateBrand").value = "";
  document.getElementById("updateName").value = "";
  document.getElementById("updateDescription").value = "";
}

function modeInitiate(){
  if(mode == 1){
    setupDrink();
  } else if(mode == 2){
    updateDrink();
  }
}

function hideAll(){
  document.getElementById("searchIdContainer").style.display="none";
  document.getElementById("updateContainer").style.display="none";
}
