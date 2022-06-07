package order;

import lombok.Data;

import java.util.ArrayList;

@Data

public class OrderResponse{
    public ArrayList<Order> orders;
    public PageInfo pageInfo;
    public ArrayList<AvailableStation> availableStations;
}
