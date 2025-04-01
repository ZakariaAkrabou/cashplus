<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.orange.cashplus.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CashPlus Mobile - Profile</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Secular+One&display=swap" rel="stylesheet">

    <script>
        tailwind.config = {
            theme: {
                extend: {
                    fontFamily: {
                        montserrat: ['Montserrat', 'sans-serif'],
                    },
                }
            }
        }
    </script>
</head>

<body class="bg-[#EAF5F5] font-montserrat">

    <nav class="bg-white py-4 px-6 shadow-sm">
        <div class="container mx-auto flex justify-between items-center">

            <div class="flex items-center">
                <img src="images/logo.svg" alt="CashPlus Mobile Logo" class="h-10">
            </div>


            <div class="hidden md:flex items-center space-x-1">
                <div class="relative group">
                    <button
                        class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Services<span
                            class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button
                        class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Become
                        a partner<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button
                        class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">The
                        group<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button
                        class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Customer
                        service<span class="ml-1">▼</span></button>
                </div>
                <div class="relative group">
                    <button
                        class="px-3 py-2 text-gray-800 font-medium hover:text-teal-600 transition-colors duration-300">Library
                        and media<span class="ml-1">▼</span></button>
                </div>
            </div>


            <div class="flex items-center space-x-2">
                <span class="text-gray-800">Find an agency</span>
                <button
                    class="bg-teal-800 text-white px-3 py-1.5 text-sm rounded hover:bg-teal-700 transition-colors duration-300">Companies</button>

            </div>
        </div>
    </nav>


    <main class="container mx-auto py-8">

        <div class="max-w-4xl mx-auto rounded-3xl overflow-hidden shadow-md">

            <div class="bg-[#002B2F] py-6 px-8 flex items-center justify-between">
                <div class="flex items-center space-x-6">
                    <div class="w-24 h-24 rounded-full overflow-hidden border-4 border-white">
                        <img src="https://placehold.co/200x200/eee/999?text=ZA" alt="Profile Picture"
                            class="w-full h-full object-cover">
                    </div>
                    <h1 class="text-white text-3xl font-medium" style="font-family: 'Secular One', sans-serif;">zakaria
                        akrabou</h1>
                </div>


                <a href ="${pageContext.request.contextPath}/logout"
                    class="bg-white text-red-500 px-4 py-2 rounded-full font-medium flex items-center space-x-2 shadow-sm hover:bg-red-500 hover:text-white hover:shadow-md transition-all duration-300">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                    </svg>
                    <span>Logout</span>
                </a>
            </div>


            <div class="bg-blue-50 p-10">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                    stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                </svg>
                            </span>
                           <input type="text" value="<%= user.getFirstname() %>" class="w-full outline-none" placeholder="First Name">

                        </div>
                    </div>


                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                    stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                </svg>
                            </span>
                            <input type="text" value="<%= user.getLastname() %>" class="w-full outline-none" placeholder="Last Name">

                        </div>
                    </div>


                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">@</span>
                            <input type="email" value="<%= user.getEmail() %>" class="w-full outline-none" placeholder="Email">

                        </div>
                    </div>


                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                    stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                                </svg>
                            </span>
                            <input type="tel" value="+212-6666666" class="w-full outline-none" placeholder="Phone">
                        </div>
                    </div>


                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                    stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
                                </svg>
                            </span>
                            <input type="password" value="" class="w-full outline-none" placeholder="Password">

                        </div>
                    </div>


                    <div class="relative">
                        <div
                            class="bg-white rounded-full p-4 flex items-center shadow-sm hover:shadow-md transition-shadow duration-300 focus-within:ring-2 focus-within:ring-teal-200 focus-within:ring-opacity-50">
                            <span class="text-gray-400 mr-3">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                    stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z" />
                                </svg>
                            </span>
                            <input type="password" value="" class="w-full outline-none"
                                placeholder="Password">
                        </div>
                    </div>
                </div>


                <div class="flex justify-center mt-10 space-x-6">
                    <button
                        class="border-2 border-yellow-400 text-gray-800 px-8 py-3 rounded-full flex items-center shadow-sm hover:bg-yellow-100 hover:-translate-y-0.5 hover:shadow-md transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                        </svg>
                        Modify Profile
                    </button>
                    <button
                        class="bg-teal-800 text-white px-8 py-3 rounded-full flex items-center shadow-sm hover:bg-teal-900 hover:-translate-y-0.5 hover:shadow-md transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M8 7H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V9a2 2 0 00-2-2h-3m-1 4l-3 3m0 0l-3-3m3 3V4" />
                        </svg>
                        Save Changes
                    </button>
                </div>
            </div>
        </div>
    </main>
</body>

</html>