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
    public class ItemsController : Controller
    {
        private DataAccessLayer
            db = new DataAccessLayer();

        // GET: Items
        public ActionResult Index()
        {
            return View(db.GetItems().ToList());
        }

        // GET: Items/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Item item = db.GetItemById(id);
            if (item == null)
            {
                return HttpNotFound();
            }
            return View(item);
        }

        // GET: Items/Create
        public ActionResult Create()
        {
            return View(new ItemVm());
        }

        // POST: Items/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(ItemVm item)
        {
            if (ModelState.IsValid)
            {

                var _item = new Item
                {
                    description = item.description,
                    id = item.id,
                    name = item.name,
                    product = db.GetProductById(item.SelectedProduct),
                    purchasePrice = item.purchasePrice,
                    quantity = item.quantity,
                    rate = item.rate,
                    sellingPrice = item.sellingPrice,
                    status = item.status,
                    unit = item.unit
                };
                db.CreateItem(_item);
                
                return RedirectToAction("Index");
            }

            return View(item);
        }

        //// GET: Items/Edit/5
        //public ActionResult Edit(string id)
        //{
        //    if (id == null)
        //    {
        //        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        //    }
        //    Item item = db.Items.Find(id);
        //    if (item == null)
        //    {
        //        return HttpNotFound();
        //    }
        //    return View(item);
        //}

        //// POST: Items/Edit/5
        //// To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        //// more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public ActionResult Edit([Bind(Include = "id,description,name,purchasePrice,quantity,rate,sellingPrice,status,type,unit")] Item item)
        //{
        //    if (ModelState.IsValid)
        //    {
        //        db.Entry(item).State = EntityState.Modified;
        //        db.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    return View(item);
        //}

        // GET: Items/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Item item = db.GetItemById(id);
            if (item == null)
            {
                return HttpNotFound();
            }
            return View(item);
        }

        // POST: Items/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            Item item = db.GetItemById(id);
            db.DeleteItem(item);
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
