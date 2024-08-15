/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const btn_view_detail = document.querySelectorAll('.btn-view-detail');
console.log(btn_view_detail);
btn_view_detail.forEach((i) => {
    i.addEventListener('click', () => {
        const order_id = i.getAttribute("order_id");
        console.log(order_id);
        location.href = "orderdetail?order-id=" + order_id;
    });
});
