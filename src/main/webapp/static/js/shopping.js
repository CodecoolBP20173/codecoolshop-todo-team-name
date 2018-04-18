function addItem(id){
    var req = new XMLHttpRequest();
    req.open('POST', '/?action=add&id=' + id);
    req.send();
    location.reload();
}

function removeItem(id){
    var req = new XMLHttpRequest();
    req.open('POST', '/?action=remove&id=' + id);
    req.send();
    location.reload();
}

function deleteItem(id){
    var req = new XMLHttpRequest();
    req.open('POST', '/?action=delete&id=' + id);
    req.send();
    location.reload();
}