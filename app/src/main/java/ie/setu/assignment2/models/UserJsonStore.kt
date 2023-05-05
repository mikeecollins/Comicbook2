
package ie.setu.assignment2.models

interface UserJsonStore {
    fun findAll(): List<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun findUser(uname:String, pass:String): UserModel?
}