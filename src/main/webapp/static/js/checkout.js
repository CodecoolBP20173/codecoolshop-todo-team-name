function copy(){
    var bcountry = document.getElementById('billcountry').value;
    var bcity = document.getElementById('billcity').value;
    var bzip = document.getElementById('billzipcode').value;
    var baddress = document.getElementById('billaddress').value;

    document.getElementById('shipcountry').value = bcountry;
    document.getElementById('shipcity').value = bcity;
    document.getElementById('shipzipcode').value = bzip;
    document.getElementById('shipaddress').value = baddress;
}