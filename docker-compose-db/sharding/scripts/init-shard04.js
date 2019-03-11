rs.initiate(
   {
      _id: "shard04",
      version: 1,
      members: [
         { _id: 0, host : "shard04a:27018" },
         { _id: 1, host : "shard04b:27018" },
         { _id: 2, host : "shard04c:27018" }
      ]
   }
)
