using Microsoft.VisualStudio.TestTools.UnitTesting;
using IMS.DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IMS.Models;

namespace IMS.DAL.Tests
{
    [TestClass()]
    public class DataAccessLayerTests
    {
        [TestMethod()]
        public void CreateMailTest()
        {
            var dal = new DataAccessLayer();
            var mail = dal.CreateMail(new Mail()
            {
                id = 1,
                message = "Test Message",
                name = "Test Name",
                to ="Me"
            });
            Assert.AreEqual(1, mail.id);
        }


        [TestMethod()]
        public void CreateCustomerTest()
        {
            var dal = new DataAccessLayer();
            var customer = new Customer()
            {
                accountBalance = 1000,
                currency = "ILS",
                customerBirthDate = "01-01-2000",
                customerFirstName = "TestFirst",
                customerLastName = "TestLast",
                email = "test@test.com",
                id = "9999",
                paymentInfo = dal.GetPaymnetById("8"),
                type = "Customer"
            };
            var custms = dal.GetCustomerById("9999");
            if (custms == null || custms.id != "9999")
            {   
                var cust = dal.CreateCustomer(customer);
                Assert.IsNotNull(cust);
                Assert.AreEqual("9999", cust.id);
            }
            else
            {
                dal.DeleteCustomer(customer);
            }

          
        }



        [TestMethod()]
        public void GetOrderstest()
        {
            var dal = new DataAccessLayer();
            var orders = dal.GetOrders();
            Assert.IsNotNull(orders);
        }


        [TestMethod()]
        public void GetItemstest()
        {
            var dal = new DataAccessLayer();
            var items = dal.GetItems();
            Assert.IsNotNull(items);
        }

        [TestMethod()]
        public void GetProductstest()
        {
            var dal = new DataAccessLayer();
            var products = dal.GetProducts();
            Assert.IsNotNull(products);
        }


        [TestMethod()]
        public void GetShipmentstest()
        {
            var dal = new DataAccessLayer();
            var shipments = dal.GetShipments();
            Assert.IsNotNull(shipments);
        }


        [TestMethod()]
        public void GetCustomerstest()
        {
            var dal = new DataAccessLayer();
            var customers = dal.GetCustomers();
            Assert.IsNotNull(customers);
        }

        [TestMethod()]
        public void GetEmployeestest()
        {
            var dal = new DataAccessLayer();
            var emps = dal.GetEmployees();
            Assert.IsNotNull(emps);
        }

    }
}