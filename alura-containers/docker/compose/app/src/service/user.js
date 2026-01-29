const redis = require('./redis')
const user = require('./../database/user')


let findUser = async () => {
    let list = await redis.getByCache()

    if (list.length !== 0) {
        console.log('cached used!')
        return list
    }

    console.log('without cache')
    list = await user.find()

    redis.setCache(list)
    return list
}

module.exports = {
    find: findUser
}
