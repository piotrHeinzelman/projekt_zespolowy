function clickProduct( me ){
    var productId = me.getAttribute("productId");
    var adr="/product/edit2/"+productId;
    alert( adr );
    $( me ).load( adr );
}

