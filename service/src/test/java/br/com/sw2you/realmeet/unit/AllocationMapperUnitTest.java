package br.com.sw2you.realmeet.unit;

import static br.com.sw2you.realmeet.utils.MapperUtils.allocationMapper;
import static br.com.sw2you.realmeet.utils.MapperUtils.roomMapper;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.sw2you.realmeet.core.BaseUnitTest;
import br.com.sw2you.realmeet.mapper.AllocationMapper;
import br.com.sw2you.realmeet.mapper.RoomMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllocationMapperUnitTest extends BaseUnitTest {
    private AllocationMapper victim;

    @BeforeEach
    void setupEach() {
        victim = allocationMapper();
    }

    @Test
    void testFromCreateAllocationDTOToEntity() {
        var createAllocationDTO = newCreateAllocationDTO();
        var room = newRoomBuilder().build();
        var allocation = victim.fromCreateAllocationDTOToEntity(createAllocationDTO, room);

        assertNull(allocation.getRoom().getId());
        assertEquals(createAllocationDTO.getSubject(), allocation.getSubject());
        assertEquals(createAllocationDTO.getEmployeeName(), allocation.getEmployee().getName());
        assertEquals(createAllocationDTO.getEmployeeEmail(), allocation.getEmployee().getEmail());
        assertEquals(createAllocationDTO.getStartAt(), allocation.getStartAt());
        assertEquals(createAllocationDTO.getEndAt(), allocation.getEndAt());
    }

    @Test
    void testFromEntityToAllocationDTO() {
        var room = newRoomBuilder().id(DEFAULT_ROOM_ID).build();
        var allocation = newAllocationBuilder(room).build();
        var allocationDTO = victim.fromEntityToAllocationDTO(allocation);

        assertEquals(allocation.getId(), allocationDTO.getId());
        assertEquals(allocation.getRoom().getId(), allocationDTO.getRoomId());
        assertNotNull(allocationDTO.getRoomId());
        assertEquals(allocation.getSubject(), allocationDTO.getSubject());
        assertEquals(allocation.getEmployee().getName(), allocationDTO.getEmployeeName());
        assertEquals(allocation.getEmployee().getEmail(), allocationDTO.getEmployeeEmail());
        assertEquals(allocation.getStartAt(), allocationDTO.getStartAt());
        assertEquals(allocation.getEndAt(), allocationDTO.getEndAt());
    }
}
