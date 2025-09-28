const { MongoClient } = require("mongodb");

const uri = "mongodb://mymongo:27017";
const client = new MongoClient(uri);

let findUser = async () => {
    try {
        await client.connect()
        const database = client.db("mysimpleapp")

        const users = database.collection('users')
        return await users.find().toArray()
    } finally {
        await client.close();
    }
}

module.exports = {
    find: findUser
}