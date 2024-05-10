function clickProduct( me ){
    var productId = me.getAttribute("productId");
    var adr="/product/edit2/"+productId;
    alert( adr );
    $( me ).load( adr );
}

function sendSort( me ){

    var adr="/addToSession/sort/"+me.value;
    $.get( adr );
    //alert( adr );

 //   alert( adr );
 //   $( me ).load( adr );
}