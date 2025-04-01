<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - CASHPLUS</title>
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
                <span class="text-gray-800">Find an agency</span>
                <button class="bg-teal-800 text-white px-3 py-1.5 text-sm rounded hover:bg-teal-700 transition-colors duration-300">Companies</button>
               
            </div>
        </div>
    </nav>
  
    <div class="min-h-screen flex items-center justify-center p-4">
        <div class="w-full max-w-6xl grid lg:grid-cols-2 gap-4">
         
            <div class="bg-[#002B2F] p-10 rounded-3xl border border-gray-700 shadow-xl text-white w-full">
                <div class="flex flex-col items-center mb-8">
                    <img src="${pageContext.request.contextPath}/images/logo.svg" alt="CASHPLUS Logo" class="h-16 mb-4">
                    <h2 class="text-3xl font-bold">Welcome Back</h2>
                    <p class="text-gray-400 text-center max-w-sm mt-2">Enter your email and password to access your account</p>
                </div>
                <form action="login" method="post" class="space-y-4">
                    <div class="relative">
                        <i class="fas fa-envelope absolute left-4 top-1/2 transform -translate-y-1/2 text-[#C3D2D7] text-lg"></i>
                        <input type="text" placeholder="Enter your email"   name="email"
                            class="block w-full px-14 py-3 text-white bg-[#0F3A44] rounded-xl border-2 border-[#C3D2D7] placeholder-[#C3D2D7] focus:outline-none focus:ring-2 focus:ring-[#C3D2D7]">
                    </div>
                    
                    <div class="relative">
                        <i class="fas fa-lock absolute left-4 top-1/2 transform -translate-y-1/2 text-[#C3D2D7] text-lg"></i>
                        <input type="password" placeholder="Enter your password"   name="password"
                            class="block w-full px-14 py-3 text-white bg-[#0F3A44] rounded-xl border-2 border-[#C3D2D7] placeholder-[#C3D2D7] focus:outline-none focus:ring-2 focus:ring-[#C3D2D7]">
                    </div>
                    
                    <div class="flex items-center justify-between text-sm">
                        <label class="flex items-center text-gray-400">
                            <a href="${pageContext.request.contextPath}/register" class="text-blue-400 hover:text-blue-300">Don't have account?</a>
                        </label>
                        <a href="#" class="text-blue-400 hover:text-blue-300">Forgot Password?</a>
                    </div>
                    <button type="submit" class="w-full py-3 mt-6 bg-[#1A5D75] rounded-xl text-white font-medium hover:bg-yellow-500">Sign In <i class="fas fa-sign-in-alt"></i></button>
                </form>
            </div>
          
            <div class="lg:flex flex-col justify-center p-8">
                <h1 class="text-5xl font-bold text-gray-700 whitespace-nowrap">
                    Welcome to <span class="text-yellow-500">CASH</span><span class="text-[#009DA7]">PLUS</span>
                </h1>
                <p class="text-lg text-gray-500 mt-4 max-w-lg">
                    There are many variations of passages of Lorem Ipsum available
                </p>
                <div class="mt-6 space-y-5">
                    <div class="flex items-center space-x-4">
                        <span class="bg-[#03222c] p-2.5 rounded-full text-yellow-500 text-lg flex items-center justify-center w-12 h-12">
                            <i class="fas fa-user-lock"></i>
                        </span>
                        <div>
                            <h3 class="text-xl font-bold text-gray-800">Personal</h3>
                            <p class="text-gray-500">There are many variations of passages</p>
                        </div>
                    </div>
            
                    <div class="flex items-center space-x-4">
                        <span class="bg-[#03222c] p-2.5 rounded-full text-yellow-500 text-lg flex items-center justify-center w-12 h-12">
                            <i class="fas fa-shield-alt"></i>
                        </span>
                        <div>
                            <h3 class="text-xl font-bold text-gray-800">Security</h3>
                            <p class="text-gray-500">There are many variations of passages</p>
                        </div>
                    </div>
            
                    <div class="flex items-center space-x-4">
                        <span class="bg-[#03222c] p-2.5 rounded-full text-yellow-500 text-lg flex items-center justify-center w-12 h-12">
                            <i class="fas fa-bolt"></i>
                        </span>
                        <div>
                            <h3 class="text-xl font-bold text-gray-800">Fast</h3>
                            <p class="text-gray-500">There are many variations of passages</p>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
</html>
