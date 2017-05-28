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


namespace IMS.Mvc.Controllers
{
    public class PaymentsController : Controller
    {
        private DataAccessLayer db = new DataAccessLayer();

        // GET: Payments
        public ActionResult Index()
        {
            return View(db.GetPayments().ToList());
        }

        // GET: Payments/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Payment payment = db.GetPaymnetById(id);
            if (payment == null)
            {
                return HttpNotFound();
            }
            return View(payment);
        }

        // GET: Payments/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Payments/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id,card,createTime,method,state,type,updateTime")] Payment payment)
        {
            if (ModelState.IsValid)
            {
                db.CreatePayment(payment);
                
                return RedirectToAction("Index");
            }

            return View(payment);
        }

        //// GET: Payments/Edit/5
        //public ActionResult Edit(string id)
        //{
        //    if (id == null)
        //    {
        //        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        //    }
        //    Payment payment = db.Payments.Find(id);
        //    if (payment == null)
        //    {
        //        return HttpNotFound();
        //    }
        //    return View(payment);
        //}

        //// POST: Payments/Edit/5
        //// To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        //// more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public ActionResult Edit([Bind(Include = "id,card,createTime,method,state,type,updateTime")] Payment payment)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        db.Entry(payment).State = EntityState.Modified;
        //        db.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    return View(payment);
        //}

        // GET: Payments/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Payment payment = db.GetPaymnetById(id);
            if (payment == null)
            {
                return HttpNotFound();
            }
            return View(payment);
        }

        // POST: Payments/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            Payment payment = db.GetPaymnetById(id);
            db.DeletePayment(payment);
            
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
