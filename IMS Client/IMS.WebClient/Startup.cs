using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(IMS.WebClient.Startup))]
namespace IMS.WebClient
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
