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
    public class ShipmentsController : Controller
    {
        private DataAccessLayer db = new DataAccessLayer();

        // GET: Shipments
        public ActionResult Index()
        {
            return View(db.GetShipments().ToList());
        }

        // GET: Shipments/Details/5
        public ActionResult Details(int id)
        {
            Shipment shipment = db.GetShipmentById(id);
            if (shipment == null)
            {
                return HttpNotFound();
            }
            return View(shipment);
        }

        // GET: Shipments/Create
        public ActionResult Create()
        {
            return View(new ShipmentVm());
        }

        // POST: Shipments/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(ShipmentVm shipment)
        {
            if (ModelState.IsValid)
            {

                var _shipment = new Shipment
                {
                    address = shipment.address,
                    id = shipment.id,
                    owner = db.GetEmployeeById(shipment.SelectedEmployee.ToString()),
                    shipDate = shipment.shipDate
                };
                db.CreateShipment(_shipment);
                return RedirectToAction("Index");
            }

            return View(shipment);
        }

        //// GET: Shipments/Edit/5
        //public ActionResult Edit(int? id)
        //{
        //    if (id == null)
        //    {
        //        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        //    }
        //    Shipment shipment = db.Shipments.Find(id);
        //    if (shipment == null)
        //    {
        //        return HttpNotFound();
        //    }
        //    return View(shipment);
        //}

        //// POST: Shipments/Edit/5
        //// To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        //// more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public ActionResult Edit([Bind(Include = "id,address,shipDate,type")] Shipment shipment)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        db.Entry(shipment).State = EntityState.Modified;
        //        db.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    return View(shipment);
        //}

        // GET: Shipments/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Shipment shipment = db.GetShipmentById(id);
            if (shipment == null)
            {
                return HttpNotFound();
            }
            return View(shipment);
        }

        // POST: Shipments/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Shipment shipment = db.GetShipmentById(id);
            db.DeleteShipment(shipment);
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
