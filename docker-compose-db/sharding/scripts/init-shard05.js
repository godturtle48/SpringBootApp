rs.initiate(
   {
      _id: "shard05",
      version: 1,
      members: [
         { _id: 0, host : "shard05a:27018" },
         { _id: 1, host : "shard05b:27018" },
         { _id: 2, host : "shard05c:27018" }
      ]
   }
)
