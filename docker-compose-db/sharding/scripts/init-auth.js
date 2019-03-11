admin = db.getSiblingDB("admin")
admin.createUser(
  {
    user: "root",
    pwd: "12345678",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)
