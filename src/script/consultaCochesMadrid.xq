
let $coches := //coche
 let $cocheMasCaro := $coches[not($coches/precio < .//precio)]  
 return data($cocheMasCaro/precio)