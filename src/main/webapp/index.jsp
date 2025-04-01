<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage - CASHPLUS</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-[#EAF5F5] min-h-screen">
   
    <nav class="bg-white py-4 px-6 shadow-sm">
        <div class="container mx-auto flex justify-between items-center">
           
            <div class="flex items-center">
                <img src="${pageContext.request.contextPath}/images/logo.svg" alt="CashPlus Mobile Logo" class="h-10">
            </div>
            
           
            <div class="hidden md:flex items-center space-x-1">
                <div class="relative group">
                    <button class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Services<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Become a partner<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">The group<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Customer service<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Library and media<span class="ml-1">▼</span></button>
                </div>
            </div>
            
          
            <div class="flex items-center space-x-2">
                <a href="${pageContext.request.contextPath}/login" class="bg-teal-800 text-white px-3 py-1.5 text-sm rounded hover:bg-teal-700 transition-colors duration-300">Login</a>
                <a href="${pageContext.request.contextPath}/register" class="bg-teal-800 text-white px-3 py-1.5 text-sm rounded hover:bg-teal-700 transition-colors duration-300">Register</a>
               
            </div>
        </div>
    </nav>

    
    <div class="container mx-auto grid grid-cols-1 lg:grid-cols-2 gap-8 py-16 px-4">
      
        <div class="flex flex-col justify-center">
            <h1 class="text-4xl md:text-5xl font-bold text-gray-800 mb-4">
                The payment account <span class="text-[#009DA7]">that makes everyday life easier!</span>
            </h1>
            
            <p class="text-gray-600 mb-6">
                Take advantage of an account with 0 DH account maintenance fees, a Mastercard payment card and multiple advantages on the Cash Plus Mobile application: Wallet to Wallet money transfer, bill payment, virtual card for your e-commerce purchases abroad...
            </p>
            
            <div class="flex flex-wrap gap-4 mb-8">
                <button class="bg-[#009DA7] text-white px-5 py-3 rounded-lg font-medium hover:bg-teal-700 transition-colors duration-300">Download the application</button>
                <button class="border border-[#009DA7] text-[#009DA7] px-5 py-3 rounded-lg font-medium hover:bg-gray-100 transition-colors duration-300">Read more</button>
            </div>
            
           
            <div class="grid grid-cols-1 md:grid-cols-2 gap-y-3 gap-x-6">
                <div class="flex items-center">
                    <span class="text-green-500 mr-2"><i class="fas fa-check"></i></span>
                    <span class="text-gray-600">Free account keeping</span>
                </div>
                <div class="flex items-center">
                    <span class="text-green-500 mr-2"><i class="fas fa-check"></i></span>
                    <span class="text-gray-600">Simple management</span>
                </div>
                <div class="flex items-center">
                    <span class="text-green-500 mr-2"><i class="fas fa-check"></i></span>
                    <span class="text-gray-600">Availability 7 days a week</span>
                </div>
                <div class="flex items-center">
                    <span class="text-green-500 mr-2"><i class="fas fa-check"></i></span>
                    <span class="text-gray-600">Instant card</span>
                </div>
                <div class="flex items-center">
                    <span class="text-green-500 mr-2"><i class="fas fa-check"></i></span>
                    <span class="text-gray-600">Secure</span>
                </div>
            </div>
        </div>
        
       
        <div class="flex items-center justify-center relative">
            <img src="${pageContext.request.contextPath}/images/Group 10.svg" alt="CASHPLUS Mobile App and Card" class="w-full max-w-lg object-contain">
        </div>
    </div>
</body>

</html>