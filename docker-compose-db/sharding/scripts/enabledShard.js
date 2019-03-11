
sh.enableSharding("ReceiptPaymentDB");

db.createCollection("CABAReceiptPayment");

sh.shardCollection("ReceiptPaymentDB.CABAReceiptPayment", {"CompanyID":hashed});

// sh.disableBalancing("ReceiptPaymentDB.CABAReceiptPayment");

// sh.addShardTag( "shard01" , "MB");

// sh.addShardTag( "shard02" , "MT");

// sh.addShardTag( "shard03" , "MN");

// // công ty 9751ee98-00a8-4d51-962e-238ed23ce170
// sh.addTagRange( 
// 	"ReceiptPaymentDB.CABAReceiptPayment", 
//     { "CompanyID" : "0-"},
//     { "CompanyID" : "5"},
// 	"MB" 
// )

// // công ty ff7084c7-cac8-412a-b851-b75727db97ab
// sh.addTagRange( 
// 	"ReceiptPaymentDB.CABAReceiptPayment", 
//     { "CompanyID" : "5-"},
//     { "CompanyID" : "9~"},
// 	"MT" 
// )
// sh.addTagRange( 
// 	"ReceiptPaymentDB.CABAReceiptPayment", 
//     { "CompanyID" : "a-"},
//     { "CompanyID" : "f~"},
// 	"MN" 
// )

// sh.enableBalancing("ReceiptPaymentDB.CABAReceiptPayment")