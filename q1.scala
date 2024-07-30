@main def main():Unit={
    var inventory1 = List(
    Product(1, "Apple", 10, 0.5),
    Product(2, "Banana", 20, 0.3),
    Product(3, "Orange", 30, 0.2)
    )

    val inventory2 = List(
    Product(1, "Apple", 10, 0.6),
    Product(2, "Banana", 20, 0.3),
    Product(3, "Orange", 30, 0.2),
    )
    productNames(inventory1)
    totalValue(inventory1)
    isEmpty(inventory1)
    println(mergeInventories(inventory1,inventory2))
    productExist(inventory1,1)

}


case class Product(id: Int,name: String,quantity: Int,price: Double)



def productNames(products: List[Product]):Unit = {
    println("-----------------\nProduct Names\n----------------")
    products.map((e)=>println(e.name))
}

def totalValue(products: List[Product]):Unit = {
    var total = 0.0
    products.map((e) => total = total + (e.price * e.quantity))

    println("---------------\nTotal Value\n---------------")
    println(total)
}

def isEmpty(products: List[Product]):Unit = {
    var totalQuantity = 0
    products.map((e) => totalQuantity = totalQuantity + e.quantity)
    if(totalQuantity == 0) println("Inventory is Empty")
    else println("Inventory is not Empty")  
}


def mergeInventories(inventory1: List[Product], inventory2: List[Product]): List[Product] = {
    val mergedInventory = inventory1.map { product1 =>
        inventory2.find(_.id == product1.id) match {
            case Some(product2) =>{
                product1.copy(quantity = product1.quantity + product2.quantity)
                product1.copy(price = product1.price max product2.price)
            }
            case None => product1
        }
    }
    mergedInventory
}

def productExist(products: List[Product],id: Int):Any = {
    products.map((e)=>{
        if(e.id == id){
            println("Product Exist")
            println("-----------------\nProduct Details\n----------------")
            println(s"Product ID: ${e.id}\nProduct Name: ${e.name}\nProduct Quantity: ${e.quantity}\nProduct Price: ${e.price}")
        }
    })
}