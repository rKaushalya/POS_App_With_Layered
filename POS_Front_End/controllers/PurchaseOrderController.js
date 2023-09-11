//load all items from the database
loadAllCustomers();
loadAllItems();
setDates();
searchCustomer();
searchItem();

function loadAllItems() {
    $("#selectItemCode").empty();
    $.ajax({
        url: BASE_URL + "item",
        dataType: "json",
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (res) {
            console.log(res);
            for (let c of res.data) {
                $("#selectItemCode").append(`<option value="${c.code}">${c.code}</option>`);
            }
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert(message);
        }
    });
}

function loadAllCustomers() {
    $("#selectCusID").empty();
    $.ajax({
        url: BASE_URL + "customer",
        dataType: "json",
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (resp) {
            console.log(resp);
            for (let cus of resp.data) {
                $("#selectCusID").append(`<option value="${cus.id}">${cus.id}</option>`);
            }
        }
    });
}

function setDates() {
    let date = new Date().toJSON().split("T")[0];
    $("#txtDate").val(date);
}

function searchCustomer(cusID) {
    let response = "";
    $.ajax({
        url: BASE_URL + "customer",
        dataType: "json",
        async: false,
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (resp) {
            response = resp.data.filter((c) => {
                return c.id == cusID;
            });
        }
    });
    return response;
}

function searchItem(code) {
    let response = "";
    $.ajax({
        url: BASE_URL + "item",
        dataType: "json",
        async: false,
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (resp) {
            response = resp.data.filter((i) => {
                return i.code == code;
            });
        }
    });
    return response;
}


$("#selectCusID").change(function () {
    let cusID = $("#selectCusID").val();
    $("#orderCustomerID").val(cusID);
    let res = searchCustomer(cusID);
    if (res.length > 0) {
        $("#orderCustomerName").val(res[0].name);
        $("#orderCustomerSalary").val(res[0].salary);
        $("#orderCustomerAddress").val(res[0].address);
    }

});


$("#selectItemCode").change(function () {
    let code = $("#selectItemCode").val();
    $("#txtItemCode").val(code);
    let res = searchItem(code);
    if (res.length > 0) {
        $("#txtItemDescription").val(res[0].description);
        $("#txtItemPrice").val(res[0].unitPrice);
        $("#txtQTYOnHand").val(res[0].qty);
    }
});

let tot = 0;

$("#btnAddToTable").click(function () {
    let code = $("#selectItemCode").val();
    let description = $("#txtItemDescription").val();
    let itemPrice = $("#txtItemPrice").val();
    let buyQty = $("#txtQty").val();
    let avQty = $("#txtQTYOnHand").val();
    let total = parseFloat(itemPrice) * parseFloat(buyQty);

    tot += total;
    $("#total").text(parseFloat(tot));

    $("#orderTable").append(`<tr><td>${code}</td><td>${description}</td><td>${itemPrice}</td><td>${avQty}</td><td>${buyQty}</td><td>${total}</td></tr>`);
});

$("#txtDiscount").click(function () {
    let total = parseFloat($("#total").text());
    let dis = parseFloat($("#txtDiscount").val());

    let discount = total/100;
    let real = discount * dis;

    let sub = parseFloat($("#subtotal").text());
    let subTotal = total-real;

    $("#subtotal").text(subTotal.toFixed(2));
});

$("#txtBalance").click(function () {
    let cash =parseFloat($("#txtCash").val());
    let sub = parseFloat($("#subtotal").text());

    let balance = cash - sub;
    let demo =parseFloat($("#txtBalance").val());
    $("#txtBalance").val(balance.toFixed(2));
});


$("#btnSubmitOrder").click(function () {
    let oId = $("#txtOrderID").val();
    let cash = $("#txtCash").val();
    let balance = $("#txtBalance").val();
    let date = $("#txtDate").val();
    let cusId = $("#orderCustomerID").val();

    $.ajax({
        url: BASE_URL + "purchase_order",
        contentType: "application/json",
        method: "post",
        dataType: "json",
        headers:{
            Auth:"user=admin,pass=admin"
        },
        data: JSON.stringify({
            "oId" : oId,
            "cash" : cash,
            "balance" : balance,
            "date" : date,
            "cusId" : cusId,
            "itemData" : getTableData()
        }),
        success: function (res) {
            alert(res.message);
            clearForm();
        },
        error: function (error) {
            console.log(error.message);
        }
    });
});

function getTableData() {
    let row = $("#orderTable").children.length;
    let array = [];
    for (let i = 0; i < row; i++) {
        let itCode = $("#orderTable").children().eq(i).children(":eq(0)").text();
        let itQty = $("#orderTable").children().eq(i).children(":eq(4)").text();
        array.push({code: itCode, qty: itQty});
    }
    return array;
}

function clearForm() {
    $("#txtOrderID").val("");
    $("#txtCash").val("");
    $("#txtBalance").val("");
    $("#txtDate").val("");
    $("#orderCustomerID").val("");
    $("#subtotal").text("00.0");
    $("#total").text("00.0");
    $("#txtItemCode").val("");
    $("#txtItemDescription").val("");
    $("#txtItemPrice").val("");
    $("#txtQty").val("");
    $("#txtQTYOnHand").val("");
    $("#txtDiscount").val("");
    $("#orderCustomerName").val("");
    $("#orderCustomerSalary").val("");
    $("#orderCustomerAddress").val("");
    $("#orderTable").empty();
}
