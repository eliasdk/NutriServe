using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using IMS.Models;
using IMS.DAL;
using IMS.Mvc.Models;

namespace IMS.Mvc.Controllers
{
    public class OrdersController : Controller
    {
        private readonly IMS.DAL.DataAccessLayer _db;

        public OrdersController()
        {
            _db = new DataAccessLayer();
        }

        // GET: Orders
        public ActionResult Index()
        {
            return View(_db.GetOrders());
        }

        // GET: Orders/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = _db.GetOrderById(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            return View(order);
        }

        // GET: Orders/Create
        public ActionResult Create()
        {
            return View(new OrdersVm());
        }

        // POST: Orders/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(OrdersVm order)
        {
            if (ModelState.IsValid)
            {
                var _order = new Order
                {
                    addedToStock = order.addedToStock,
                    customer = _db.GetCustomerById(order.SelectedCustomer),
                    dateplaced = order.dateplaced,
                    dateReceived = order.dateReceived,
                    dateStringed = order.dateStringed,
                    id = order.id,
                    number = order.number,
                    outstanding = order.outstanding,
                    payment = _db.GetPaymnetById(order.SelectedPayment)
                };
                var selectedItems = new List<Item> {_db.GetItemById(order.SelectedItem)};
                _order.purchasedItems = selectedItems;
                _order.quantity = order.quantity;
                _order.shipment = _db.GetShipmentById(int.Parse(order.SelectedShipment));
                _order.total = order.total;
                _db.CreateOrder(_order);
                return RedirectToAction("Index");
            }

            return View(order);
        }

        // GET: Orders/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = _db.GetOrderById(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            return View(order);
        }

        //// POST: Orders/Edit/5
        //// To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        //// more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public ActionResult Edit([Bind(Include = "id,addedToStock,dateReceived,dateStringed,dateplaced,number,outstanding,quantity,total,type")] Order order)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        db.Entry(order).State = EntityState.Modified;
        //        db.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    return View(order);
        //}

        // GET: Orders/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = _db.GetOrderById(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            return View(order);
        }

        // POST: Orders/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            Order order = _db.GetOrderById(id);
            _db.DeleteOrder(order);
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            base.Dispose(disposing);
        }
    }
}
