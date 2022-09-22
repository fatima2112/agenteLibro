package agents;

import jade.core.Agent;
import behaviours.RequestPerformer;
import gui.BookBuyerGui;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BookBuyerAgent extends Agent {
  private String bookTitle;
  //private String bookTitle2;
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private BookBuyerAgent this_agent = this;
  private BookBuyerGui gui;
  
  protected void setup() {
    System.out.println("Buyer agent " + getAID().getName() + " is ready");
    
    gui = new BookBuyerGui(this);
    gui.showGui();
    
    //Argumentos para un agente por medio de una gui
    
    //Object[] args = getArguments();
    //if(args != null && args.length > 0) {
      //bookTitle = (String)args[0];
      bookTitle = (String) this_agent.getBookTitle();
      //System.out.println("Book: " + bookTitle);
      
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
          
          System.out.println("Trying to buy " + bookTitle);
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("book-selling");
          template.addServices(sd);
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            System.out.println("Found the following seller agents:");
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              System.out.println(sellerAgents[i].getName());
            }
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    //} else {
      //System.out.println("No target book title specified");
      //doDelete();
    //}
  }
  
  protected void takeDown() {
    System.out.println("Buyer agent " + getAID().getName() + " terminating");
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public void setBookTitle(String BookTitle){
      this.bookTitle = BookTitle;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
  
}
