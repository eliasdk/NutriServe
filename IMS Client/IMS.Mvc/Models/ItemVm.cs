using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using IMS.DAL;
using IMS.Models;

namespace IMS.Mvc.Models
{
    public class ItemVm : Item
    {

        [Display(Name = "Product")]
        public string SelectedProduct { get; set; }


        public List<Product> Products { get; set; }


        private DataAccessLayer dl;
        public ItemVm()
        {
            dl = new DataAccessLayer();
            Products = dl.GetProducts();
        }
    }
}