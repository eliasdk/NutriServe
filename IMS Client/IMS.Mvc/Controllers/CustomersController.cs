using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using IMS.DAL;
using IMS.Models;
using IMS.Mvc.Models;


namespace IMS.Mvc.Controllers
{
    public class CustomersController : Controller
    {
        private DataAccessLayer db = new DataAccessLayer();

        // GET: Customers
        public ActionResult Index()
        {
            return View(db.GetCustomers().ToList());
        }

        // GET: Customers/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Customer customer = db.GetCustomerById(id);
            if (customer == null)
            {
                return HttpNotFound();
            }
            return View(customer);
        }

        // GET: Customers/Create
        public ActionResult Create()
        {
            return View(new CustomerVm());
        }

        // POST: Customers/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(CustomerVm customer)
        {
            if (ModelState.IsValid)
            {
                var _customer = new Customer
                {
                    accountBalance = customer.accountBalance,
                    currency = customer.currency,
                    customerBirthDate = customer.customerBirthDate,
                    customerFirstName = customer.customerBirthDate,
                    customerLastName = customer.customerLastName,
                    email = customer.email,
                    id = customer.id,
                    paymentInfo = db.GetPaymnetById(customer.SelectedPayment),
                    type = customer.type
                };
                db.CreateCustomer(_customer);
                return RedirectToAction("Index");
            }

            return View(customer);
        }

        //// GET: Customers/Edit/5
        //public ActionResult Edit(string id)
        //{
        //    if (id == null)
        //    {
        //        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        //    }
        //    Customer customer = db.Customers.Find(id);
        //    if (customer == null)
        //    {
        //        return HttpNotFound();
        //    }
        //    return View(customer);
        //}

        //// POST: Customers/Edit/5
        //// To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        //// more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public ActionResult Edit([Bind(Include = "id,accountBalance,currency,customerBirthDate,customerFirstName,customerLastName,email,type")] Customer customer)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        db.Entry(customer).State = EntityState.Modified;
        //        db.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    return View(customer);
        //}

        // GET: Customers/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Customer customer = db.GetCustomerById(id);
            if (customer == null)
            {
                return HttpNotFound();
            }
            return View(customer);
        }

        // POST: Customers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            Customer customer = db.GetCustomerById(id);
            db.DeleteCustomer(customer);
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
            }
            base.Dispose(disposing);
        }
    }
}
