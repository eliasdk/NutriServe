using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using IMS.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using RestSharp;

namespace IMS.DAL
{
    public class DataAccessLayer
    {


        public DataAccessLayer()
        {
            
        }

        public List<T> GetObjects<T>(string objectName)
        {
            var client = new RestClient("http://31.168.143.199:8080/inventory");
            var request = new RestRequest($"api/{objectName}", Method.GET);
            var response = (RestResponse<List<T>>)(client.Execute<List<T>>(request));
            return response.Data ?? new List<T>();
        }

        public T CreateOject<T>(T toCreate,string objectName) where T : new()
        {
            var client = new RestClient("http://31.168.143.199:8080/inventory");
            var request = new RestRequest($"api/{objectName}", Method.POST) { RequestFormat = DataFormat.Json };
            request.AddJsonBody(toCreate);
            var response =client.Execute<T>(request);
            var result = response.Data;
            if (result == null)
            {
                return new T();
            }
            return response.Data;
        }

        public bool DeleteOject<T>(T toCreate, string objectName) where T : new()
        {
            var client = new RestClient("http://31.168.143.199:8080/inventory");
            var request = new RestRequest($"api/{objectName}", Method.DELETE) { RequestFormat = DataFormat.Json };
            request.AddJsonBody(toCreate);
            var response = client.Execute<T>(request);
            return response.StatusCode == System.Net.HttpStatusCode.NoContent;
        }

        public Mail CreateMail(Mail mail)
        {
            return CreateOject<Mail>(mail, "mail");
        }

        public List<Order> GetOrders()
        {
            return GetObjects<Order>("order");
        }

        public Order GetOrderById(string id)
        {
            return GetOrders().SingleOrDefault(o => o.id==id);
        }

        public Order CreateOrder(Order order)
        {
            //var client = new RestClient("http://31.168.143.199:8080/inventory");
            //var request = new RestRequest("api/order", Method.POST) { RequestFormat = DataFormat.Json };
            //request.AddJsonBody(order);
            //var response = (RestResponse<Order>)(client.Execute<Order>(request));
            //return response.Data;
            return CreateOject<Order>(order, "order");
        }

        public bool DeleteOrder(Order order)
        {
            return DeleteOject<Order>(order, "order");
        }

    

        public List<Product> GetProducts()
        {
            return GetObjects<Product>("product");
        }


        public Product GetProductById(string id)
        {
            return GetProducts().SingleOrDefault(o => o.id == id);
        }

        public Product CreateProduct(Product product)
        {
            return CreateOject<Product>(product, "product");
        }


        public bool DeleteProduct(Product product)
        {
            return DeleteOject<Product>(product, "product");
        }

    


        public List<Shipment> GetShipments()
        {
            return GetObjects<Shipment>("shipment");
        }

        public Shipment GetShipmentById(int? id)
        {
            return GetShipments().SingleOrDefault(o => o.id == id);
        }

        public Shipment CreateShipment(Shipment shipment)
        {
            return CreateOject<Shipment>(shipment, "shipment");
        }

        public bool DeleteShipment(Shipment shipment)
        {
            return DeleteOject<Shipment>(shipment, "shipment");
        }

        public List<Customer> GetCustomers()
        {
            return GetObjects<Customer>("customer");
        }

        public Customer GetCustomerById(string id)
        {
            return GetCustomers().SingleOrDefault(o => o.id == id);
        }


        public Customer CreateCustomer(Customer customer)
        {
            return CreateOject<Customer>(customer, "customer");
        }

        public bool DeleteCustomer(Customer customer)
        {
            return DeleteOject<Customer>(customer, "customer");
        }

        public List<Item> GetItems()
        {
            return GetObjects<Item>("item");
        }

        public Item GetItemById(string id)
        {
            return GetItems().SingleOrDefault(o => o.id == id);
        }

        public Item CreateItem(Item item)
        {
            return CreateOject<Item>(item, "item");
        }

        public bool DeleteItem(Item item)
        {
            return DeleteOject<Item>(item, "item");
        }

        public List<Employee> GetEmployees()
        {
            return GetObjects<Employee>("employee");
        }

        public Employee GetEmployeeById(string id)
        {
            return GetEmployees().SingleOrDefault(o => o.id == id);
        }

        public Employee CreateEmployee(Employee employee)
        {
            return CreateOject<Employee>(employee, "employee");
        }

        public bool DeleteEmployee(Employee employee)
        {
            return DeleteOject<Employee>(employee, "employee");
        }

        public List<Payment> GetPayments()
        {
            return GetObjects<Payment>("payment");
        }
        public Payment GetPaymnetById(string id)
        {
            return GetPayments().SingleOrDefault(o => o.id == id);
        }

        public Payment CreatePayment(Payment payment)
        {
            return CreateOject<Payment>(payment, "payment");
        }

        public bool DeletePayment(Payment payment)
        {
            return DeleteOject<Payment>(payment, "payment");
        }

    }
}
