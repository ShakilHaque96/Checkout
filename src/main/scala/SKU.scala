
sealed trait SKU { val price: Double }

case object A extends SKU { val price: Double = 50 }
case object B extends SKU { val price: Double = 30 }
case object C extends SKU { val price: Double = 20 }
case object D extends SKU { val price: Double = 15 }
